(module

(memory 1)
(data (i32.const 0)
)
(func $svc (param $i i32) (param $n i32) (result i32)
(local $res i32)
(if(i32.eq
(local.get $n)
(i32.const 0)
)
(then
(i32.const 0)
(local.set $res)
)(else
(i32.add
(local.get $i)
(i32.const 1)
)
(i32.sub
(local.get $n)
(i32.const 1)
)
(call $svc)
 (local.set $res)
)
)(local.get $res)
)
(func $main (export "main") (param $len i32) (result i32)
(local.get $len)
(i32.const 4)
(call $svc)
 )
)