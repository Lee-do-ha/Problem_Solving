SELECT F.FLAVOR
FROM FIRST_HALF F
WHERE F.TOTAL_ORDER >= 3000 AND (SELECT I.INGREDIENT_TYPE
                              FROM ICECREAM_INFO I
                              WHERE F.FLAVOR = I.FLAVOR) = 'FRUIT_BASED'