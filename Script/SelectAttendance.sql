select
(select user.first_name 
from user where user.id = sc.user_id) as teacher,
s.name,cl.name, sc.date, cg.time_start, r.name, a.is_present, sc.status, 
(select ur.picture
from attendance atd
Join registration rgt on atd.registration_id = rgt.id
Join user ur on rgt.user_id = ur.id
where atd.schedule_id = sc.id and atd.is_present = true 
limit 5)  as picture
From user u 
Join registration rg on u.id = rg.user_id
Join class cl on rg.class_id = cl.id
Join class_term ct on cl.id = ct.class_id
Join schedule sc on ct.id = sc.class_term_id
Join category cg on sc.category_id = cg.id
Join subject s on sc.subject_id = s.id
Join room r on sc.room_id = r.id
Join attendance a on a.schedule_id = sc.id and a.registration_id = rg.id
where u.id = 2


