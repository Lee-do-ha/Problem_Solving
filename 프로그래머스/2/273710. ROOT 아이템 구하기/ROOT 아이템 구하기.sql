SELECT B.ITEM_ID, (SELECT A.ITEM_NAME
                FROM ITEM_INFO A
                WHERE B.ITEM_ID = A.ITEM_ID) AS ITEM_NAME
FROM ITEM_TREE B
WHERE B.PARENT_ITEM_ID IS NULL
ORDER BY B.ITEM_ID