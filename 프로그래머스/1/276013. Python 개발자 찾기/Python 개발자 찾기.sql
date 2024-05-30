SELECT
    ID,
    EMAIL,
    FIRST_NAME,
    LAST_NAME
FROM
    DEVELOPER_INFOS
WHERE
    SKILL_1 LIKE 'Python' OR
    SKILL_2 LIKE 'Python' OR
    SKILL_3 LIKE 'Python'
ORDER BY
    ID ASC
