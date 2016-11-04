CREATE TABLE user_info (
  id         INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  card_id    TEXT                          NOT NULL,
  name       TEXT                          NOT NULL,
  department TEXT                          NOT NULL,
  same_id    TEXT DEFAULT NULL
)