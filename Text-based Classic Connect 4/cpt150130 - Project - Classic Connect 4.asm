# Project - Classic Connect 4
# Christopher tePoele - cpt150130

.data
startupMsg:		.asciiz		"Welcome to my digital version of the classic game: Connect 4!"
exitMsg:		.asciiz		"~ Thank you for playing ~"
playerTokenMsg:		.asciiz		"Player 1 is represented by  '~'. Player 2 is represented by '+'."
playerChoiceErrorMsg:	.asciiz		"Your chosen column has no room left. Please choose another column."
player1sTurnMsgCol:	.asciiz		"It's Player 1's turn. Choose which column you want to place your game piece. (1-7)"
player2sTurnMsgCol:	.asciiz		"It's Player 2's turn. Choose which column you want to place your game piece. (1-7)"
askIfWinnerMsg:		.asciiz		"Have you made 4 in a row yet?"
player1Winner:		.asciiz		"Player 1 Wins!"
player2Winner:		.asciiz		"Player 2 Wins!"
whosTurn:		.word		1
chosenCol:		.word		0
firstRun:		.word		1
#playingBoard:		.word		1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2
playingBoard:		.word		0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
updatedGameTable:	.asciiz		"~~~~~~~~~~~~~~~~ CONNECT 4 ~~~~~~~~~~~~~~~~"
boardSpacing: 		.asciiz		" ===== ===== ===== ===== ===== ===== ===== "
col1Empty:		.asciiz		"|     |"
col2Thru7Empty:	 	.asciiz		"     |"
col1XPt1:		.asciiz		"| ~~~ |"
col1XPt2:		.asciiz		"| ~~~ |"
col2Thru7XPt1:		.asciiz		" ~~~ |"
col2Thru7XPt2:		.asciiz		" ~~~ |"
col1OPt1:		.asciiz		"| +++ |"
col1OPt2:		.asciiz		"| +++ |"
col2Thru7OPt1:		.asciiz		" +++ |"
col2Thru7OPt2:		.asciiz		" +++ |"


.text
main:
	li	$v0, 55
	la	$a0, startupMsg			# Initial greeting message
	li	$a1, 1
	syscall

	li	$v0, 55
	la	$a0, playerTokenMsg		# Player token information
	syscall
	
	jal gameTime				# Starting the game
	
	li	$v0, 55
	la	$a0, exitMsg			# Exit message
	li	$a1, 1
	syscall
	
	j exit
	
gameTime:
	addi 	$sp, $sp, -4
	sw	$ra, ($sp)			# Saving returning address for exit time
	la 	$s7, playingBoard

	j displayBoard				# Draw empty game board
	
firstRunDone:
	sw	$zero, firstRun			# Marking first run as complete
	
player1sTurn:
	li	$t1, 1
	sw	$t1, whosTurn			# Marking turn as Player 1's
	
	li	$v0, 51
	la	$a0, player1sTurnMsgCol		# Asking for user to choose column 1 - 7
	syscall
	
	bne	$a1, 0, player1sTurn		# Testing for bad input
	li	$t2, 1
	slt	$t1, $a0, $t2
	beq	$t1, 1, player1sTurn		# Testing to make sure input is 1 or above
	li	$t2, 8
	slt	$t1, $a0, $t2
	bne	$t1, 1, player1sTurn		# Testing to make sure input is 7 or below
	
	sw	$a0, chosenCol			# Saving valid input
	
	jal inputPlayerChoice			# Running input through checks and deciding proper row for placement
	j displayBoard				# Drawing updated board with new input added
	
player2sTurn:
	li	$t1, 2
	sw	$t1, whosTurn			# Marking turn as Player 1's
	
	li	$v0, 51
	la	$a0, player2sTurnMsgCol		# Asking for user to choose column 1 - 7
	syscall
	
	bne	$a1, 0, player2sTurn		# Testing for bad input
	li	$t2, 1
	slt	$t1, $a0, $t2
	beq	$t1, 1, player2sTurn		# Testing to make sure input is 1 or above
	li	$t2, 8
	slt	$t1, $a0, $t2
	bne	$t1, 1, player2sTurn		# Testing to make sure input is 7 or below
	sw	$a0, chosenCol
	
	jal inputPlayerChoice			# Running input through checks and deciding proper row for placement
	j displayBoard				# Drawing updated board with new input added
	
playerChoiceError:
	li	$v0, 55
	la	$a0, playerChoiceErrorMsg	# Message if chosen column if full
	li	$a1, 0
	syscall
	
	lw 	$t1, whosTurn			# Restarting player input with same player
	beq	$t1, 1, player1sTurn
	beq	$t1, 2, player2sTurn
	
winnerCheck:
	li	$v0, 50
	la	$a0, askIfWinnerMsg		# Message asking for winning confirmation
	syscall
	bne 	$a0, 0, whosNext		# If yes is not chosen, rotate to next player
	
	j player1Won				# If yes is chosen, current player wins
	
whosNext:
	lw	$t1, whosTurn			# Rotating to next player
	beq	$t1, 1, player2sTurn
	beq	$t1, 2, player1sTurn
	
	
player1Won:
	lw	$t1, whosTurn
	bne	$t1, 1, player2Won		# If not Player 1, go to Player 2 winning exit
	
	li	$v0, 55
	la	$a0, player1Winner		# Player 1's winning exit
	li	$a1, 1
	syscall
	
	j exitGame
	
player2Won:
	li	$v0, 55
	la	$a0, player2Winner		# Player 2's winning exit
	li	$a1, 1
	syscall
	
	j exitGame

exitGame:  
	lw	$ra, ($sp)
	addi	$sp, $sp, 4			# Restoring address to exit game
	jr	$ra
	
displayBoard:
	j 	row1				# Initiating game table drawing
	
# ========================================
# ============== Board Rows ==============
# ========================================
	
row1:
	li	$v0, 4
	la	$a0, updatedGameTable
	syscall
	
	jal printNewLine
	jal printNewLine
	
	li	$v0, 4
	la	$a0, boardSpacing
	syscall
	
	jal printNewLine
	
row2:
	lw	$t1, 0($s7)			# Buffer corresponds to array position where player inputs are stored
	move 	$v0, $ra
	addi 	$ra, $ra, 36			# Buffering return address to next spot
	beq	$t1, 0, outputCol1Empty		# Testing if spot is taken or not
	beq	$t1, 1, outputCol1XPt1		# 0 = free, 1 = Player 1 Taken, 2 = Player 2 Taken
	beq	$t1, 2, outputCol1OPt1
	
	lw	$t2, 4($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t2, 0, outputCol2Thru7Empty
	beq	$t2, 1, outputCol2Thru7XPt1
	beq	$t2, 2, outputcol2Thru7OPt1
	
	lw	$t3, 8($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t3, 0, outputCol2Thru7Empty
	beq	$t3, 1, outputCol2Thru7XPt1
	beq	$t3, 2, outputcol2Thru7OPt1
	
	lw	$t4, 12($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t4, 0, outputCol2Thru7Empty
	beq	$t4, 1, outputCol2Thru7XPt1
	beq	$t4, 2, outputcol2Thru7OPt1
	
	lw	$t5, 16($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t5, 0, outputCol2Thru7Empty
	beq	$t5, 1, outputCol2Thru7XPt1
	beq	$t5, 2, outputcol2Thru7OPt1
	
	lw	$t6, 20($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t6, 0, outputCol2Thru7Empty
	beq	$t6, 1, outputCol2Thru7XPt1
	beq	$t6, 2, outputcol2Thru7OPt1
	
	lw	$t7, 24($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t7, 0, outputCol2Thru7Empty
	beq	$t7, 1, outputCol2Thru7XPt1
	beq	$t7, 2, outputcol2Thru7OPt1
	
	jal printNewLine
	
row3:
	lw	$t1, 0($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t1, 0, outputCol1Empty
	beq	$t1, 1, outputCol1XPt2
	beq	$t1, 2, outputCol1OPt2
	
	lw	$t2, 4($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t2, 0, outputCol2Thru7Empty
	beq	$t2, 1, outputCol2Thru7XPt2
	beq	$t2, 2, outputcol2Thru7OPt2
	
	lw	$t3, 8($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t3, 0, outputCol2Thru7Empty
	beq	$t3, 1, outputCol2Thru7XPt2
	beq	$t3, 2, outputcol2Thru7OPt2
	
	lw	$t4, 12($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t4, 0, outputCol2Thru7Empty
	beq	$t4, 1, outputCol2Thru7XPt2
	beq	$t4, 2, outputcol2Thru7OPt2
	
	lw	$t5, 16($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t5, 0, outputCol2Thru7Empty
	beq	$t5, 1, outputCol2Thru7XPt2
	beq	$t5, 2, outputcol2Thru7OPt2
	
	lw	$t6, 20($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t6, 0, outputCol2Thru7Empty
	beq	$t6, 1, outputCol2Thru7XPt2
	beq	$t6, 2, outputcol2Thru7OPt2
	
	lw	$t7, 24($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t7, 0, outputCol2Thru7Empty
	beq	$t7, 1, outputCol2Thru7XPt2
	beq	$t7, 2, outputcol2Thru7OPt2
	
	jal printNewLine
	
row4:
	li	$v0, 4
	la	$a0, boardSpacing
	syscall
	
	jal printNewLine
	
row5:
	lw	$t1, 28($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t1, 0, outputCol1Empty
	beq	$t1, 1, outputCol1XPt1
	beq	$t1, 2, outputCol1OPt1
	
	lw	$t2, 32($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t2, 0, outputCol2Thru7Empty
	beq	$t2, 1, outputCol2Thru7XPt1
	beq	$t2, 2, outputcol2Thru7OPt1
	
	lw	$t3, 36($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t3, 0, outputCol2Thru7Empty
	beq	$t3, 1, outputCol2Thru7XPt1
	beq	$t3, 2, outputcol2Thru7OPt1
	
	lw	$t4, 40($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t4, 0, outputCol2Thru7Empty
	beq	$t4, 1, outputCol2Thru7XPt1
	beq	$t4, 2, outputcol2Thru7OPt1
	
	lw	$t5, 44($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t5, 0, outputCol2Thru7Empty
	beq	$t5, 1, outputCol2Thru7XPt1
	beq	$t5, 2, outputcol2Thru7OPt1
	
	lw	$t6, 48($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t6, 0, outputCol2Thru7Empty
	beq	$t6, 1, outputCol2Thru7XPt1
	beq	$t6, 2, outputcol2Thru7OPt1
	
	lw	$t7, 52($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t7, 0, outputCol2Thru7Empty
	beq	$t7, 1, outputCol2Thru7XPt1
	beq	$t7, 2, outputcol2Thru7OPt1
	
	jal printNewLine
	
row6:
	lw	$t1, 28($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t1, 0, outputCol1Empty
	beq	$t1, 1, outputCol1XPt2
	beq	$t1, 2, outputCol1OPt2
	
	lw	$t2, 32($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t2, 0, outputCol2Thru7Empty
	beq	$t2, 1, outputCol2Thru7XPt2
	beq	$t2, 2, outputcol2Thru7OPt2
	
	lw	$t3, 36($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t3, 0, outputCol2Thru7Empty
	beq	$t3, 1, outputCol2Thru7XPt2
	beq	$t3, 2, outputcol2Thru7OPt2
	
	lw	$t4, 40($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t4, 0, outputCol2Thru7Empty
	beq	$t4, 1, outputCol2Thru7XPt2
	beq	$t4, 2, outputcol2Thru7OPt2
	
	lw	$t5, 44($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t5, 0, outputCol2Thru7Empty
	beq	$t5, 1, outputCol2Thru7XPt2
	beq	$t5, 2, outputcol2Thru7OPt2
	
	lw	$t6, 48($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t6, 0, outputCol2Thru7Empty
	beq	$t6, 1, outputCol2Thru7XPt2
	beq	$t6, 2, outputcol2Thru7OPt2
	
	lw	$t7, 52($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t7, 0, outputCol2Thru7Empty
	beq	$t7, 1, outputCol2Thru7XPt2
	beq	$t7, 2, outputcol2Thru7OPt2
	
	jal printNewLine
	
row7:
	li	$v0, 4
	la	$a0, boardSpacing
	syscall
	
	jal printNewLine
	
row8:
	lw	$t1, 56($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t1, 0, outputCol1Empty
	beq	$t1, 1, outputCol1XPt1
	beq	$t1, 2, outputCol1OPt1
	
	lw	$t2, 60($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t2, 0, outputCol2Thru7Empty
	beq	$t2, 1, outputCol2Thru7XPt1
	beq	$t2, 2, outputcol2Thru7OPt1
	
	lw	$t3, 64($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t3, 0, outputCol2Thru7Empty
	beq	$t3, 1, outputCol2Thru7XPt1
	beq	$t3, 2, outputcol2Thru7OPt1
	
	lw	$t4, 68($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t4, 0, outputCol2Thru7Empty
	beq	$t4, 1, outputCol2Thru7XPt1
	beq	$t4, 2, outputcol2Thru7OPt1
	
	lw	$t5, 72($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t5, 0, outputCol2Thru7Empty
	beq	$t5, 1, outputCol2Thru7XPt1
	beq	$t5, 2, outputcol2Thru7OPt1
	
	lw	$t6, 76($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t6, 0, outputCol2Thru7Empty
	beq	$t6, 1, outputCol2Thru7XPt1
	beq	$t6, 2, outputcol2Thru7OPt1
	
	lw	$t7, 80($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t7, 0, outputCol2Thru7Empty
	beq	$t7, 1, outputCol2Thru7XPt1
	beq	$t7, 2, outputcol2Thru7OPt1
	
	jal printNewLine
	
row9:
	lw	$t1, 56($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t1, 0, outputCol1Empty
	beq	$t1, 1, outputCol1XPt2
	beq	$t1, 2, outputCol1OPt2
	
	lw	$t2, 60($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t2, 0, outputCol2Thru7Empty
	beq	$t2, 1, outputCol2Thru7XPt2
	beq	$t2, 2, outputcol2Thru7OPt2
	
	lw	$t3, 64($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t3, 0, outputCol2Thru7Empty
	beq	$t3, 1, outputCol2Thru7XPt2
	beq	$t3, 2, outputcol2Thru7OPt2
	
	lw	$t4, 68($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t4, 0, outputCol2Thru7Empty
	beq	$t4, 1, outputCol2Thru7XPt2
	beq	$t4, 2, outputcol2Thru7OPt2
	
	lw	$t5, 72($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t5, 0, outputCol2Thru7Empty
	beq	$t5, 1, outputCol2Thru7XPt2
	beq	$t5, 2, outputcol2Thru7OPt2
	
	lw	$t6, 76($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t6, 0, outputCol2Thru7Empty
	beq	$t6, 1, outputCol2Thru7XPt2
	beq	$t6, 2, outputcol2Thru7OPt2
	
	lw	$t7, 80($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t7, 0, outputCol2Thru7Empty
	beq	$t7, 1, outputCol2Thru7XPt2
	beq	$t7, 2, outputcol2Thru7OPt2
	
	jal printNewLine
	
row10:
	li	$v0, 4
	la	$a0, boardSpacing
	syscall
	
	jal printNewLine
	
row11:
	lw	$t1, 84($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t1, 0, outputCol1Empty
	beq	$t1, 1, outputCol1XPt1
	beq	$t1, 2, outputCol1OPt1
	
	lw	$t2, 88($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t2, 0, outputCol2Thru7Empty
	beq	$t2, 1, outputCol2Thru7XPt1
	beq	$t2, 2, outputcol2Thru7OPt1
	
	lw	$t3, 92($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t3, 0, outputCol2Thru7Empty
	beq	$t3, 1, outputCol2Thru7XPt1
	beq	$t3, 2, outputcol2Thru7OPt1
	
	lw	$t4, 96($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t4, 0, outputCol2Thru7Empty
	beq	$t4, 1, outputCol2Thru7XPt1
	beq	$t4, 2, outputcol2Thru7OPt1
	
	lw	$t5, 100($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t5, 0, outputCol2Thru7Empty
	beq	$t5, 1, outputCol2Thru7XPt1
	beq	$t5, 2, outputcol2Thru7OPt1
	
	lw	$t6, 104($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t6, 0, outputCol2Thru7Empty
	beq	$t6, 1, outputCol2Thru7XPt1
	beq	$t6, 2, outputcol2Thru7OPt1
	
	lw	$t7, 108($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t7, 0, outputCol2Thru7Empty
	beq	$t7, 1, outputCol2Thru7XPt1
	beq	$t7, 2, outputcol2Thru7OPt1
	
	jal printNewLine
	
row12:
	lw	$t1, 84($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t1, 0, outputCol1Empty
	beq	$t1, 1, outputCol1XPt2
	beq	$t1, 2, outputCol1OPt1
	
	lw	$t2, 88($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t2, 0, outputCol2Thru7Empty
	beq	$t2, 1, outputCol2Thru7XPt2
	beq	$t2, 2, outputcol2Thru7OPt2
	
	lw	$t3, 92($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t3, 0, outputCol2Thru7Empty
	beq	$t3, 1, outputCol2Thru7XPt2
	beq	$t3, 2, outputcol2Thru7OPt2
	
	lw	$t4, 96($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t4, 0, outputCol2Thru7Empty
	beq	$t4, 1, outputCol2Thru7XPt2
	beq	$t4, 2, outputcol2Thru7OPt2
	
	lw	$t5, 100($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t5, 0, outputCol2Thru7Empty
	beq	$t5, 1, outputCol2Thru7XPt2
	beq	$t5, 2, outputcol2Thru7OPt2
	
	lw	$t6, 104($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t6, 0, outputCol2Thru7Empty
	beq	$t6, 1, outputCol2Thru7XPt2
	beq	$t6, 2, outputcol2Thru7OPt2
	
	lw	$t7, 108($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t7, 0, outputCol2Thru7Empty
	beq	$t7, 1, outputCol2Thru7XPt2
	beq	$t7, 2, outputcol2Thru7OPt2
	
	jal printNewLine
	
row13:
	li	$v0, 4
	la	$a0, boardSpacing
	syscall
	
	jal printNewLine
	
row14:
	lw	$t1, 112($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t1, 0, outputCol1Empty
	beq	$t1, 1, outputCol1XPt1
	beq	$t1, 2, outputCol1OPt1
	
	lw	$t2, 116($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t2, 0, outputCol2Thru7Empty
	beq	$t2, 1, outputCol2Thru7XPt1
	beq	$t2, 2, outputcol2Thru7OPt1
	
	lw	$t3, 120($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t3, 0, outputCol2Thru7Empty
	beq	$t3, 1, outputCol2Thru7XPt1
	beq	$t3, 2, outputcol2Thru7OPt1
	
	lw	$t4, 124($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t4, 0, outputCol2Thru7Empty
	beq	$t4, 1, outputCol2Thru7XPt1
	beq	$t4, 2, outputcol2Thru7OPt1
	
	lw	$t5, 128($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t5, 0, outputCol2Thru7Empty
	beq	$t5, 1, outputCol2Thru7XPt1
	beq	$t5, 2, outputcol2Thru7OPt1
	
	lw	$t6, 132($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t6, 0, outputCol2Thru7Empty
	beq	$t6, 1, outputCol2Thru7XPt1
	beq	$t6, 2, outputcol2Thru7OPt1
	
	lw	$t7, 136($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t7, 0, outputCol2Thru7Empty
	beq	$t7, 1, outputCol2Thru7XPt1
	beq	$t7, 2, outputcol2Thru7OPt1
	
	jal printNewLine
	
row15:
	lw	$t1, 112($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t1, 0, outputCol1Empty
	beq	$t1, 1, outputCol1XPt2
	beq	$t1, 2, outputCol1OPt2
	
	lw	$t2, 116($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t2, 0, outputCol2Thru7Empty
	beq	$t2, 1, outputCol2Thru7XPt2
	beq	$t2, 2, outputcol2Thru7OPt2
	
	lw	$t3, 120($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t3, 0, outputCol2Thru7Empty
	beq	$t3, 1, outputCol2Thru7XPt2
	beq	$t3, 2, outputcol2Thru7OPt2
	
	lw	$t4, 124($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t4, 0, outputCol2Thru7Empty
	beq	$t4, 1, outputCol2Thru7XPt2
	beq	$t4, 2, outputcol2Thru7OPt2
	
	lw	$t5, 128($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t5, 0, outputCol2Thru7Empty
	beq	$t5, 1, outputCol2Thru7XPt2
	beq	$t5, 2, outputcol2Thru7OPt2
	
	lw	$t6, 132($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t6, 0, outputCol2Thru7Empty
	beq	$t6, 1, outputCol2Thru7XPt2
	beq	$t6, 2, outputcol2Thru7OPt2
	
	lw	$t7, 136($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t7, 0, outputCol2Thru7Empty
	beq	$t7, 1, outputCol2Thru7XPt2
	beq	$t7, 2, outputcol2Thru7OPt2
	
	jal printNewLine
	
row16:
	li	$v0, 4
	la	$a0, boardSpacing
	syscall
	
	jal printNewLine
	
row17:
	lw	$t1, 140($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t1, 0, outputCol1Empty
	beq	$t1, 1, outputCol1XPt1
	beq	$t1, 2, outputCol1OPt1
	
	lw	$t2, 144($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t2, 0, outputCol2Thru7Empty
	beq	$t2, 1, outputCol2Thru7XPt1
	beq	$t2, 2, outputcol2Thru7OPt1
	
	lw	$t3, 148($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t3, 0, outputCol2Thru7Empty
	beq	$t3, 1, outputCol2Thru7XPt1
	beq	$t3, 2, outputcol2Thru7OPt1
	
	lw	$t4, 152($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t4, 0, outputCol2Thru7Empty
	beq	$t4, 1, outputCol2Thru7XPt1
	beq	$t4, 2, outputcol2Thru7OPt1
	
	lw	$t5, 156($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t5, 0, outputCol2Thru7Empty
	beq	$t5, 1, outputCol2Thru7XPt1
	beq	$t5, 2, outputcol2Thru7OPt1
	
	lw	$t6, 160($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t6, 0, outputCol2Thru7Empty
	beq	$t6, 1, outputCol2Thru7XPt1
	beq	$t6, 2, outputcol2Thru7OPt1
	
	lw	$t7, 164($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t7, 0, outputCol2Thru7Empty
	beq	$t7, 1, outputCol2Thru7XPt1
	beq	$t7, 2, outputcol2Thru7OPt1
	
	jal printNewLine
	
row18:
	lw	$t1, 140($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t1, 0, outputCol1Empty
	beq	$t1, 1, outputCol1XPt2
	beq	$t1, 2, outputCol1OPt2
	
	lw	$t2, 144($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t2, 0, outputCol2Thru7Empty
	beq	$t2, 1, outputCol2Thru7XPt2
	beq	$t2, 2, outputcol2Thru7OPt2
	
	lw	$t3, 148($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t3, 0, outputCol2Thru7Empty
	beq	$t3, 1, outputCol2Thru7XPt2
	beq	$t3, 2, outputcol2Thru7OPt2
	
	lw	$t4, 152($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t4, 0, outputCol2Thru7Empty
	beq	$t4, 1, outputCol2Thru7XPt2
	beq	$t4, 2, outputcol2Thru7OPt2
	
	lw	$t5, 156($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t5, 0, outputCol2Thru7Empty
	beq	$t5, 1, outputCol2Thru7XPt2
	beq	$t5, 2, outputcol2Thru7OPt2
	
	lw	$t6, 160($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t6, 0, outputCol2Thru7Empty
	beq	$t6, 1, outputCol2Thru7XPt2
	beq	$t6, 2, outputcol2Thru7OPt2
	
	lw	$t7, 164($s7)
	move 	$v0, $ra
	addi 	$ra, $ra, 36
	beq	$t7, 0, outputCol2Thru7Empty
	beq	$t7, 1, outputCol2Thru7XPt2
	beq	$t7, 2, outputcol2Thru7OPt2
	
	jal printNewLine
	
row19:
	li	$v0, 4
	la	$a0, boardSpacing
	syscall
	
	jal printNewLine
	
	lw	$t1, firstRun			# Draw empty table on first run
	beq	$t1, 1, firstRunDone
	
	j winnerCheck				# Loop to winner checking

# ========================================
# ============ End Board Rows ============
# ========================================
	
# ========================================
# ============ Board Elements ============
# ========================================

outputCol1Empty:
	li	$v0, 4
	la	$a0, col1Empty			# Actual output of table elements referred to from above
	syscall
	
	jr $ra
	
outputCol2Thru7Empty:
	li	$v0, 4
	la	$a0, col2Thru7Empty
	syscall
	
	jr $ra
	
outputCol1XPt1:
	li	$v0, 4
	la	$a0, col1XPt1
	syscall
	
	jr $ra
	
outputCol1XPt2:
	li	$v0, 4
	la	$a0, col1XPt2
	syscall
	
	jr $ra
	
outputCol2Thru7XPt1:
	li	$v0, 4
	la	$a0, col2Thru7XPt1
	syscall
	
	jr $ra
	
outputCol2Thru7XPt2:
	li	$v0, 4
	la	$a0, col2Thru7XPt2
	syscall
	
	jr $ra
	
outputCol1OPt1:
	li	$v0, 4
	la	$a0, col1OPt1
	syscall
	
	jr $ra
	
outputCol1OPt2:
	li	$v0, 4
	la	$a0, col1OPt2
	syscall
	
	jr $ra
	
outputcol2Thru7OPt1:
	li	$v0, 4
	la	$a0, col2Thru7OPt1
	syscall
	
	jr $ra
	
outputcol2Thru7OPt2:
	li	$v0, 4
	la	$a0, col2Thru7OPt2
	syscall
	
	jr $ra
	
# ========================================
# ========== End Board Elements ==========
# ========================================
	
printNewLine:
	addi $a0, $0, 0xA			# Printing a fresh new line
        addi $v0, $0, 0xB
        syscall
	
	jr $ra
	
# ========================================
# ============ Player Choices ============
# ========================================
	
inputPlayerChoice:
	lw 	$s1, chosenCol
	beq	$s1, 1, playerChoseCol1
	beq	$s1, 2, playerChoseCol2
	beq	$s1, 3, playerChoseCol3		# Taking user input and testing for appropriate placement
	beq	$s1, 4, playerChoseCol4
	beq	$s1, 5, playerChoseCol5
	beq	$s1, 6, playerChoseCol6
	beq	$s1, 7, playerChoseCol7
	
# =============== Column 1 ===============

playerChoseCol1:
	j	playerChoseCol1Row6
	
playerChoseCol1Row6:
	lw	$t1, 140($s7)			# Buffer corresponds to array of user inputs
	beq	$t1, 1, playerChoseCol1Row5	# If currently taken, move up a row
	beq	$t1, 2, playerChoseCol1Row5	# Else, record answer in array
	lw	$t2, whosTurn
	sw	$t2, 140($s7)
	
	jr $ra
	
playerChoseCol1Row5:
	lw	$t1, 112($s7)
	beq	$t1, 1, playerChoseCol1Row4
	beq	$t1, 2, playerChoseCol1Row4
	lw	$t2, whosTurn
	sw	$t2, 112($s7)
	
	jr $ra

playerChoseCol1Row4:
	lw	$t1, 84($s7)
	beq	$t1, 1, playerChoseCol1Row3
	beq	$t1, 2, playerChoseCol1Row3
	lw	$t2, whosTurn
	sw	$t2, 84($s7)
	
	jr $ra

playerChoseCol1Row3:
	lw	$t1, 56($s7)
	beq	$t1, 1, playerChoseCol1Row2
	beq	$t1, 2, playerChoseCol1Row2
	lw	$t2, whosTurn
	sw	$t2, 56($s7)
	
	jr $ra

playerChoseCol1Row2:
	lw	$t1, 28($s7)
	beq	$t1, 1, playerChoseCol1Row1
	beq	$t1, 2, playerChoseCol1Row1
	lw	$t2, whosTurn
	sw	$t2, 28($s7)
	
	jr $ra

playerChoseCol1Row1:
	lw	$t1, 0($s7)
	beq	$t1, 1, playerChoiceError	# If whole column is full, ask for a new input
	beq	$t1, 2, playerChoiceError
	lw	$t2, whosTurn
	sw	$t2, 0($s7)
	
	jr $ra
	

# =============== Column 2 ===============
	
playerChoseCol2:
	j	playerChoseCol2Row6
	
playerChoseCol2Row6:
	lw	$t1, 144($s7)
	beq	$t1, 1, playerChoseCol2Row5
	beq	$t1, 2, playerChoseCol2Row5
	lw	$t2, whosTurn
	sw	$t2, 144($s7)
	
	jr $ra
	
playerChoseCol2Row5:
	lw	$t1, 116($s7)
	beq	$t1, 1, playerChoseCol2Row4
	beq	$t1, 2, playerChoseCol2Row4
	lw	$t2, whosTurn
	sw	$t2, 116($s7)
	
	jr $ra

playerChoseCol2Row4:
	lw	$t1, 88($s7)
	beq	$t1, 1, playerChoseCol2Row3
	beq	$t1, 2, playerChoseCol2Row3
	lw	$t2, whosTurn
	sw	$t2, 88($s7)
	
	jr $ra

playerChoseCol2Row3:
	lw	$t1, 60($s7)
	beq	$t1, 1, playerChoseCol2Row2
	beq	$t1, 2, playerChoseCol2Row2
	lw	$t2, whosTurn
	sw	$t2, 60($s7)
	
	jr $ra

playerChoseCol2Row2:
	lw	$t1, 32($s7)
	beq	$t1, 1, playerChoseCol2Row1
	beq	$t1, 2, playerChoseCol2Row1
	lw	$t2, whosTurn
	sw	$t2, 32($s7)
	
	jr $ra

playerChoseCol2Row1:
	lw	$t1, 4($s7)
	beq	$t1, 1, playerChoiceError
	beq	$t1, 2, playerChoiceError
	lw	$t2, whosTurn
	sw	$t2, 4($s7)
	
	jr $ra
	
# =============== Column 3 ===============
	
playerChoseCol3:
	j	playerChoseCol3Row6
	
playerChoseCol3Row6:
	lw	$t1, 148($s7)
	beq	$t1, 1, playerChoseCol3Row5
	beq	$t1, 2, playerChoseCol3Row5
	lw	$t2, whosTurn
	sw	$t2, 148($s7)
	
	jr $ra
	
playerChoseCol3Row5:
	lw	$t1, 120($s7)
	beq	$t1, 1, playerChoseCol3Row4
	beq	$t1, 2, playerChoseCol3Row4
	lw	$t2, whosTurn
	sw	$t2, 120($s7)
	
	jr $ra

playerChoseCol3Row4:
	lw	$t1, 92($s7)
	beq	$t1, 1, playerChoseCol3Row3
	beq	$t1, 2, playerChoseCol3Row3
	lw	$t2, whosTurn
	sw	$t2, 92($s7)
	
	jr $ra

playerChoseCol3Row3:
	lw	$t1, 64($s7)
	beq	$t1, 1, playerChoseCol3Row2
	beq	$t1, 2, playerChoseCol3Row2
	lw	$t2, whosTurn
	sw	$t2, 64($s7)
	
	jr $ra

playerChoseCol3Row2:
	lw	$t1, 36($s7)
	beq	$t1, 1, playerChoseCol3Row1
	beq	$t1, 2, playerChoseCol3Row1
	lw	$t2, whosTurn
	sw	$t2, 36($s7)
	
	jr $ra

playerChoseCol3Row1:
	lw	$t1, 8($s7)
	beq	$t1, 1, playerChoiceError
	beq	$t1, 2, playerChoiceError
	lw	$t2, whosTurn
	sw	$t2, 8($s7)
	
	jr $ra
	
# =============== Column 4 ===============
	
playerChoseCol4:
	j	playerChoseCol4Row6
	
playerChoseCol4Row6:
	lw	$t1, 152($s7)
	beq	$t1, 1, playerChoseCol4Row5
	beq	$t1, 2, playerChoseCol4Row5
	lw	$t2, whosTurn
	sw	$t2, 152($s7)
	
	jr $ra
	
playerChoseCol4Row5:
	lw	$t1, 124($s7)
	beq	$t1, 1, playerChoseCol4Row4
	beq	$t1, 2, playerChoseCol4Row4
	lw	$t2, whosTurn
	sw	$t2, 124($s7)
	
	jr $ra

playerChoseCol4Row4:
	lw	$t1, 96($s7)
	beq	$t1, 1, playerChoseCol4Row3
	beq	$t1, 2, playerChoseCol4Row3
	lw	$t2, whosTurn
	sw	$t2, 96($s7)
	
	jr $ra

playerChoseCol4Row3:
	lw	$t1, 68($s7)
	beq	$t1, 1, playerChoseCol4Row2
	beq	$t1, 2, playerChoseCol4Row2
	lw	$t2, whosTurn
	sw	$t2, 68($s7)
	
	jr $ra

playerChoseCol4Row2:
	lw	$t1, 40($s7)
	beq	$t1, 1, playerChoseCol4Row1
	beq	$t1, 2, playerChoseCol4Row1
	lw	$t2, whosTurn
	sw	$t2, 40($s7)
	
	jr $ra

playerChoseCol4Row1:
	lw	$t1, 12($s7)
	beq	$t1, 1, playerChoiceError
	beq	$t1, 2, playerChoiceError
	lw	$t2, whosTurn
	sw	$t2, 12($s7)
	
	jr $ra
	
# =============== Column 5 ===============
	
playerChoseCol5:
	j	playerChoseCol5Row6
	
playerChoseCol5Row6:
	lw	$t1, 156($s7)
	beq	$t1, 1, playerChoseCol5Row5
	beq	$t1, 2, playerChoseCol5Row5
	lw	$t2, whosTurn
	sw	$t2, 156($s7)
	
	jr $ra
	
playerChoseCol5Row5:
	lw	$t1, 128($s7)
	beq	$t1, 1, playerChoseCol5Row4
	beq	$t1, 2, playerChoseCol5Row4
	lw	$t2, whosTurn
	sw	$t2, 128($s7)
	
	jr $ra

playerChoseCol5Row4:
	lw	$t1, 100($s7)
	beq	$t1, 1, playerChoseCol5Row3
	beq	$t1, 2, playerChoseCol5Row3
	lw	$t2, whosTurn
	sw	$t2, 100($s7)
	
	jr $ra

playerChoseCol5Row3:
	lw	$t1, 72($s7)
	beq	$t1, 1, playerChoseCol5Row2
	beq	$t1, 2, playerChoseCol5Row2
	lw	$t2, whosTurn
	sw	$t2, 72($s7)
	
	jr $ra

playerChoseCol5Row2:
	lw	$t1, 44($s7)
	beq	$t1, 1, playerChoseCol5Row1
	beq	$t1, 2, playerChoseCol5Row1
	lw	$t2, whosTurn
	sw	$t2, 44($s7)
	
	jr $ra

playerChoseCol5Row1:
	lw	$t1, 16($s7)
	beq	$t1, 1, playerChoiceError
	beq	$t1, 2, playerChoiceError
	lw	$t2, whosTurn
	sw	$t2, 16($s7)
	
	jr $ra
	
# =============== Column 6 ===============
	
playerChoseCol6:
	j	playerChoseCol6Row6
	
playerChoseCol6Row6:
	lw	$t1, 160($s7)
	beq	$t1, 1, playerChoseCol6Row5
	beq	$t1, 2, playerChoseCol6Row5
	lw	$t2, whosTurn
	sw	$t2, 160($s7)
	
	jr $ra
	
playerChoseCol6Row5:
	lw	$t1, 132($s7)
	beq	$t1, 1, playerChoseCol6Row4
	beq	$t1, 2, playerChoseCol6Row4
	lw	$t2, whosTurn
	sw	$t2, 132($s7)
	
	jr $ra
 
playerChoseCol6Row4:
	lw	$t1, 104($s7)
	beq	$t1, 1, playerChoseCol6Row3
	beq	$t1, 2, playerChoseCol6Row3
	lw	$t2, whosTurn
	sw	$t2, 104($s7)
	
	jr $ra
  
playerChoseCol6Row3:
	lw	$t1, 76($s7)
	beq	$t1, 1, playerChoseCol6Row2
	beq	$t1, 2, playerChoseCol6Row2
	lw	$t2, whosTurn
	sw	$t2, 76($s7)
	
	jr $ra

playerChoseCol6Row2:
	lw	$t1, 48($s7)
	beq	$t1, 1, playerChoseCol6Row1
	beq	$t1, 2, playerChoseCol6Row1
	lw	$t2, whosTurn
	sw	$t2, 48($s7)
	
	jr $ra

playerChoseCol6Row1:
	lw	$t1, 20($s7)
	beq	$t1, 1, playerChoiceError
	beq	$t1, 2, playerChoiceError
	lw	$t2, whosTurn
	sw	$t2, 20($s7)
	
	jr $ra
	
# =============== Column 7 ===============
	
playerChoseCol7:
	j	playerChoseCol7Row6
	
playerChoseCol7Row6:
	lw	$t1, 164($s7)
	beq	$t1, 1, playerChoseCol7Row5
	beq	$t1, 2, playerChoseCol7Row5
	lw	$t2, whosTurn
	sw	$t2, 164($s7)
	
	jr $ra
	
playerChoseCol7Row5:
	lw	$t1, 136($s7)
	beq	$t1, 1, playerChoseCol7Row4
	beq	$t1, 2, playerChoseCol7Row4
	lw	$t2, whosTurn
	sw	$t2, 136($s7)
	
	jr $ra

playerChoseCol7Row4:
	lw	$t1, 108($s7)
	beq	$t1, 1, playerChoseCol7Row3
	beq	$t1, 2, playerChoseCol7Row3
	lw	$t2, whosTurn
	sw	$t2, 108($s7)
	
	jr $ra

playerChoseCol7Row3:
	lw	$t1, 80($s7)
	beq	$t1, 1, playerChoseCol7Row2
	beq	$t1, 2, playerChoseCol7Row2
	lw	$t2, whosTurn
	sw	$t2, 80($s7)
	
	jr $ra

playerChoseCol7Row2:
	lw	$t1, 52($s7)
	beq	$t1, 1, playerChoseCol7Row1
	beq	$t1, 2, playerChoseCol7Row1
	lw	$t2, whosTurn
	sw	$t2, 52($s7)
	
	jr $ra

playerChoseCol7Row1:
	lw	$t1, 24($s7)
	beq	$t1, 1, playerChoiceError
	beq	$t1, 2, playerChoiceError
	lw	$t2, whosTurn
	sw	$t2, 24($s7)
	
	jr $ra

# ========================================
# ========== End Player Choices ==========
# ========================================

exit:						# Errorless Exit
	li $v0, 10
	syscall
