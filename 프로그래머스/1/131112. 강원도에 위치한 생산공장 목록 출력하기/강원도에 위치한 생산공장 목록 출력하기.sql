SELECT
    FACTORY_ID,
    FACTORY_NAME,
    ADDRESS
FROM
    FOOD_FACTORY
WHERE
    ADDRESS LIKE '강원도%'
ORDER BY
    FACTORY_ID ASC