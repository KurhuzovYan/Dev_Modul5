SELECT name, project_count
FROM (SELECT name, count(*) AS project_count
      FROM client
               JOIN project p on client.client_id = p.client_id
      GROUP BY name, p.client_id) as count_proj
WHERE project_count = (SELECT max(project_count)
                       FROM (SELECT name, count(*) AS project_count
                             FROM client
                                      JOIN project p on client.client_id = p.client_id
                             GROUP BY name, p.client_id) as max_count)