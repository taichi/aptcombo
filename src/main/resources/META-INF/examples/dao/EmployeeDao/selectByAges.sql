select /*%expand*/* from employee where 
/*%for age : ages */
  age = /* age */30
  /*%if age_has_next */
  /*# "or" */
  /*%end */
/*%end */
