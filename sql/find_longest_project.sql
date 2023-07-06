SELECT project_id, month_count
FROM (SELECT project_id,
             EXTRACT(year FROM finish_date) * 12 + EXTRACT(month FROM finish_date) -
             EXTRACT(year FROM start_date) * 12 - EXTRACT(month FROM start_date) AS month_count
      FROM project) as subquery
WHERE month_count = (SELECT max(month_count)
                     FROM (SELECT EXTRACT(year FROM finish_date) * 12 + EXTRACT(month FROM finish_date) -
                                  EXTRACT(year FROM start_date) * 12 - EXTRACT(month FROM start_date) AS month_count
                           FROM project) as max_month)




