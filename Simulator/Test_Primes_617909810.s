;Test 3
;Calculate the first 5 primes from 10
;Author: DAI Zhenlong
;Date:   08.05.13

addi r0,r1,10	;count of primes
addi r0,r2,2	;begin with 2
addi r0,r7,256	;r7 = 256, position to be stored
begin:
beqz r1,end	;if count =  0, halt
add r30,r30,r30
addi r0,r11,2
div  r2,r11,r3	;half of the number to be tested
addi r3,r3,1
addi r0,r4,2	;loop from 2
loop:
sub  r3,r4,r5   ;r5 = r3 - r4
beqz r5,store
add r30,r30,r30
div  r2,r4,r8	;r8 = r2/r4
mult r4,r8,r9	;r9 = r8*r4
sub  r2,r9,r10	;r10 = r2-r9
beqz r10,next	;r10 == r2, break
add r30,r30,r30
addi r4,r4,1	;r4++
j    loop
add r30,r30,r30
store:
sw   0(r7),r2   ;store r2 in 0(r7)
addi r7,r7,4	;r7 += 4
subi r1,r1,1	;r1--
next:
addi r2,r2,1	;r2++
j    begin
add r30,r30,r30
end:
halt
