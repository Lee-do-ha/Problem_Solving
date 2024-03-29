-- 코드를 입력하세요
SELECT DISTINCT R.CAR_ID 
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY R
WHERE DATEDIFF(R.START_DATE, DATE_FORMAT('2022-10-01', '%Y-%m-%d')) >= 0 
    AND R.CAR_ID IN (SELECT C.CAR_ID
                    FROM CAR_RENTAL_COMPANY_CAR C
                    WHERE CAR_TYPE = '세단')
ORDER BY R.CAR_ID DESC;


# SELECT C.CAR_ID
# FROM CAR_RENTAL_COMPANY_CAR C
# WHERE CAR_TYPE ='세단'