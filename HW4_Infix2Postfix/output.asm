
li	$t0,2

li	$t1,3

li	$t2,12

li	$t3,3
div	$t3,$t0
mflo	$t3
li	$t4,2
li	$t5,5
mult	$t4,$t5
mflo	$t4
li	$t5,2
div	$t3,$t5
mflo	$t3
add	$t3,$t3,$t4
li	$t4,9
add	$t3,$t3,$t4
move	$t1,$t3

li	$t2,9

mult	$t1,$t2
mflo	$t3
move	$t1,$t3

move	$a0,$t1
li	$v0,1
syscall