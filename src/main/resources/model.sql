CREATE TABLE user_info (
  id         INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  card_id    TEXT                              NOT NULL,
  name       TEXT                              NOT NULL,
  department TEXT                              NOT NULL,
  same_id    TEXT      DEFAULT NULL,
  create_at  TIMESTAMP DEFAULT (DATETIME('now', 'localtime')),
  modify_at  TIMESTAMP DEFAULT (DATETIME('now', 'localtime'))
);
CREATE UNIQUE INDEX idx_card_depart
  ON user_info (card_id, department);