SELECT p.project_id, sum(salary * months) as project_cost
FROM (SELECT project_id,
             extract(year from finish_date) * 12 + extract(month from finish_date) -
             extract(year from start_date) * 12 - extract(month from start_date) as months
      FROM project) as p
         JOIN
     project_worker pw ON p.project_id = pw.project_id
         JOIN
     worker w ON pw.worker_id = w.worker_id
GROUP BY p.project_id
ORDER BY project_cost DESC

