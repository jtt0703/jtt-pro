# 数据表设计说明

本文档用于说明 `docs/schema.sql` 中的数据模型设计，重点描述智能问答知识库系统在用户记忆、会话归档、知识切块与长期记忆存储方面的表结构划分。

## 设计目标

数据库层主要承接以下职责：

- 存储用户画像与偏好标签
- 归档完整会话消息
- 保存长期记忆摘要与向量索引映射关系
- 保存知识库文档切块后的结构化元数据

在整体架构中：

- **MySQL** 负责结构化元数据持久化
- **Redis** 负责短期会话缓存
- **Elasticsearch** 负责 BM25 检索
- **向量库** 负责 embedding 检索与语义召回

---

## 1. `user_profile`

用于存储用户画像与长期偏好信息。

核心字段说明：

- `user_id`：用户唯一标识
- `user_name`：用户名
- `preferred_topics`：用户关注主题
- `preference_tags`：偏好标签，用于个性化召回与回答增强
- `created_at` / `updated_at`：创建与更新时间

典型用途：

- 个性化问答
- 用户兴趣建模
- 长期记忆召回增强

---

## 2. `conversation_archive`

用于归档完整会话记录。

核心字段说明：

- `session_id`：会话 ID
- `user_id`：用户 ID
- `role`：消息角色，`user` / `assistant` / `system`
- `content`：消息内容
- `message_type`：消息类型，如 `chat` / `summary` / `tool`
- `created_at`：消息时间

典型用途：

- 会话审计与追溯
- 对话摘要生成
- 历史上下文回放

---

## 3. `long_memory_record`

用于存储长期记忆摘要与向量索引关联信息。

核心字段说明：

- `session_id`：来源会话 ID
- `user_id`：用户 ID
- `summary`：高价值对话摘要
- `embedding_id`：向量化后的索引 ID
- `importance_score`：重要度分数，用于长期记忆筛选与优先级控制
- `created_at`：写入时间

典型用途：

- 记录高价值对话沉淀结果
- 建立 MySQL 元数据与向量库索引之间的映射
- 为语义相似度召回提供结构化入口

---

## 4. `knowledge_chunk`

用于存储知识库文档切块结果。

核心字段说明：

- `document_id`：原始文档 ID
- `title`：文档标题
- `chunk_content`：分块后的文本内容
- `keywords`：关键词，用于 BM25 与标签过滤
- `chunk_index`：切块顺序
- `created_at`：入库时间

典型用途：

- 知识分块持久化
- 检索索引构建
- 召回结果溯源

---

## 数据流说明

一次完整的问答链路通常涉及以下数据流：

1. 用户消息先进入短期记忆窗口，并同步到 Redis
2. 会话消息按需归档到 `conversation_archive`
3. 高价值会话经过摘要后写入 `long_memory_record`
4. 长期记忆摘要在向量库中建立 `embedding_id` 对应索引
5. 知识文档切块后写入 `knowledge_chunk`，并同步构建 Elasticsearch / 向量索引
6. 查询阶段结合 `user_profile`、长期记忆与知识切块信息生成最终召回上下文

---

## 设计说明

当前表结构以表达系统核心数据边界为主，适合后续继续扩展：

- 增加租户字段，支持多租户知识库
- 增加文档状态字段，支持知识审核与发布
- 增加 trace_id / request_id，支持链路审计
- 增加反馈表，支持回答质量评估与在线优化
