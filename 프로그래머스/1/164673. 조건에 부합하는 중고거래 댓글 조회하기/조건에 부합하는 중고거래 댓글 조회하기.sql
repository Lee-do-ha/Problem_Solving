# SELECT B.TITLE, B.BOARD_ID, R.REPLY_ID, R.WRITER_ID, R.CONTENTS, SUBSTRING(B.CREATED_DATE, 1, 10) AS CREATED_DATE
# FROM USED_GOODS_REPLY R
# JOIN USED_GOODS_BOARD B
# WHERE R.BOARD_ID = B.BOARD_ID AND B.CREATED_DATE LIKE "2022-10%"
# ORDER BY R.CREATED_DATE ASC, B.CREATED_DATE ASC;

SELECT B.TITLE, B.BOARD_ID, R.REPLY_ID, R.WRITER_ID, R.CONTENTS, DATE_FORMAT(R.CREATED_DATE, '%Y-%m-%d') AS CRAETED_DATE
FROM USED_GOODS_REPLY R
JOIN USED_GOODS_BOARD B
WHERE R.BOARD_ID = B.BOARD_ID AND B.CREATED_DATE LIKE "2022-10%"
ORDER BY R.CREATED_DATE ASC, B.TITLE ASC;