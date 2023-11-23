-- 코드를 입력하세요
SELECT B.BOARD_ID, B.WRITER_ID, B.TITLE, B.PRICE, CASE B.STATUS
                                                    WHEN "DONE" THEN "거래완료"
                                                    WHEN "SALE" THEN "판매중"
                                                    WHEN "RESERVED" THEN "예약중"
                                                    END AS STATUS
FROM USED_GOODS_BOARD B
WHERE B.CREATED_DATE LIKE "2022-10-05%"
ORDER BY B.BOARD_ID DESC;