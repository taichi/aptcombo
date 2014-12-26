select /*%expand*/* from employee 
where 
  hiredate >= /* @roundDownTimePart(from) */'2001-01-01 12:34:56'
  and 
  hiredate < /* @roundUpTimePart(to) */'2001-01-01 12:34:56'