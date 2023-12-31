SELECT ROW_NUMBER() OVER ()                                                      AS "Номер",
       CONCAT("Н_ЛЮДИ"."ИМЯ", ' ', "Н_ЛЮДИ"."ФАМИЛИЯ", ' ', "Н_ЛЮДИ"."ОТЧЕСТВО") AS "ФИО",
       AVG("Н_ВЕДОМОСТИ"."ОЦЕНКА"::INTEGER)                                      AS "Ср_оценка"
FROM "Н_ЛЮДИ"
         JOIN "Н_ВЕДОМОСТИ" ON "Н_ЛЮДИ"."ИД" = "Н_ВЕДОМОСТИ"."ЧЛВК_ИД"
    AND "Н_ВЕДОМОСТИ"."ОЦЕНКА" IN ('2', '3', '4', '5')
         JOIN "Н_УЧЕНИКИ" ON "Н_ЛЮДИ"."ИД" = "Н_УЧЕНИКИ"."ЧЛВК_ИД"
    AND "Н_УЧЕНИКИ"."ГРУППА" = '4100'
GROUP BY "Н_ЛЮДИ"."ИД", "Н_ЛЮДИ"."ИМЯ", "Н_ЛЮДИ"."ФАМИЛИЯ", "Н_ЛЮДИ"."ОТЧЕСТВО"
HAVING AVG("Н_ВЕДОМОСТИ"."ОЦЕНКА"::INTEGER) > (SELECT MIN("Н_ВЕДОМОСТИ"."ОЦЕНКА"::INTEGER)
                                               FROM "Н_ВЕДОМОСТИ"
                                                        JOIN "Н_УЧЕНИКИ"
                                                             ON "Н_ВЕДОМОСТИ"."ЧЛВК_ИД" = "Н_УЧЕНИКИ"."ЧЛВК_ИД"
                                                                 AND "Н_УЧЕНИКИ"."ГРУППА" = '1101'
                                               WHERE "Н_ВЕДОМОСТИ"."ОЦЕНКА" IN ('2', '3', '4', '5'));