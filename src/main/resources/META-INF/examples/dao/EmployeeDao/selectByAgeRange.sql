select /*%expand*/* from employee
where
/*%if min != null */
  age >= /* min */10
/*%end */
/*%if max != null */
  and
  age <= /* max */70
/*%end */
order by age