SELECT 'YOUNGEST' as type, name, birthday
FROM worker
WHERE birthday = (SELECT max(birthday) FROM worker)
UNION
SELECT 'OLDEST' as type, name, birthday
FROM worker
WHERE birthday = (SELECT min(birthday) FROM worker)

