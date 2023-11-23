-- 코드를 입력하세요
SELECT R.FOOD_TYPE, R.REST_ID, R.REST_NAME, R.FAVORITES
FROM REST_INFO R
WHERE (R.FOOD_TYPE, R.FAVORITES) IN (SELECT R.FOOD_TYPE, MAX(R.FAVORITES)
FROM REST_INFO R
GROUP BY R.FOOD_TYPE)
ORDER BY R.FOOD_TYPE DESC;