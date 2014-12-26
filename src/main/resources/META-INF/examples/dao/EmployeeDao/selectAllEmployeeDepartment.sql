select
  e.*,
  d.name department_name 
from 
  employee e 
left outer join 
  department d 
on 
  e.department_id = d.id 
order by 
  e.id