select /*%expand*/* from employee where name like /* @suffix(suffix) */'%X' escape '$'