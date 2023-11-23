-- 코드를 입력하세요
# SELECT * 
# FROM FIRST_HALF

SELECT J.FLAVOR
FROM JULY J
GROUP BY J.FLAVOR
ORDER BY SUM(J.TOTAL_ORDER) + (SELECT SUM(F.TOTAL_ORDER)
                                      FROM FIRST_HALF F
                                      WHERE F.FLAVOR = J.FLAVOR) DESC
LIMIT 3