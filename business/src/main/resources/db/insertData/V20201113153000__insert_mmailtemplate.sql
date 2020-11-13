
DELETE FROM m_mail_template WHERE MAIL_TEMPLATE_ID IN (1, 2);
INSERT INTO m_mail_template (MAIL_TEMPLATE_ID, TEMPLATE_DIV, TITLE, TEXT, REGIST_TIME, UPDATE_TIME, DELETE_FLG, VERSION) VALUES
(1, 1, '会員仮登録完了のお知らせ', '[[${dto.familyName}]] 様\r\n\r\nご登録いただき、ありがとうございます。\r\n現在、仮登録の状態ですので、下記URLをクリックして登録を完了させてください。\r\n\r\nhttps://[[${dto.domain}]]/entry/regist/[[${dto.onetimeKey}]]/\r\n\r\n', now(), now(), 0, 1),
(2, 1, '会員本登録完了のお知らせ', '[[${dto.familyName}]] 様\r\n\r\nこの度はご登録いただき、ありがとうございます。\r\n[[${dto.familyName}]]様の会員登録手続きが完了しました。\r\n\r\n------------------------------------------------------------------------\r\n会員専用ページへは、下記URLより、\r\nご登録いただいたメールアドレスとパスワードを入力してログインしてください。\r\n\r\nhttps://[[${dto.domain}]]/login/\r\n------------------------------------------------------------------------\r\n\r\n今後ともよろしくお願い致します。\r\n', now(), now(), 0, 1),
(3, 1, '新パスワード設定画面のお知らせ', '[[${dto.familyName}]] \r\n\r\n新パスワード設定画面のURLをお知らせします。\r\n下記URLをクリックして、新しいパスワードを設定してください。\r\n\r\nhttps://[[${dto.domain}]]/entry/remind/config/[[${dto.onetimeKey}]]/\r\n\r\n', now(), now(), 0, 1);
