SELECT A.ID, A.GENOTYPE, (SELECT B.GENOTYPE
                                  FROM ECOLI_DATA B
                                  WHERE A.PARENT_ID = B.ID) AS PARENT_GENOTYPE
FROM ECOLI_DATA A
WHERE A.PARENT_ID IS NOT NULL AND (SELECT B.GENOTYPE
                                  FROM ECOLI_DATA B
                                  WHERE A.PARENT_ID = B.ID) & A.GENOTYPE >= (SELECT B.GENOTYPE
                                  FROM ECOLI_DATA B
                                  WHERE A.PARENT_ID = B.ID)