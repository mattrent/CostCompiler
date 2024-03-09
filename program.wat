(module

(memory 1)
(data (i32.const 0)
)
(func $svc (param $i i32) (param $n i32) (result i32)
(i32.mul
(local.get $n)
(local.get $i)
)
)
(func $main (export "main") (param $len i32) (result i32)
(local.get $len)
(i32.const 4)
(call $svc)
 )
)