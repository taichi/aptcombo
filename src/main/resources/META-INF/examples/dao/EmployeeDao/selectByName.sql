select /*%expand*/* from employee
where 
/*%if name != null*/
  name = /*name*/'hoge'
/*%else */
  and
  name is null
/*%end */