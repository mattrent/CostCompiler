(module

(memory 1)
(data (i32.const 0)
)
(func $svc (param $i i32) (param $n i32) (result i32)
(local $res i32)
(if(i32.eq
(local.get $i)
(local.get $n)
)
(then
(i32.const 1)
(local.set $res)
)(else
(i32.const 0)
(local.set $res)
)
)(local.get $res)
)
(func $main (export "main") (param $a i32) (param $b i32) (result i32)
(local.get $a)
(local.get $b)
(call $svc)
 )
)