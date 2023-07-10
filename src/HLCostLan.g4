grammar HLCostLan;

func : '(' params ')' '=>' '{' stm '}' ;

/* p contains the list of paramters of the function */

params :  ID','  params | ID ;

stm :(
     'call'ID'('exp(','exp)*')' stm          |
     'if' '(' cond ')' '{'stm '}' 'else' '{' stm '}' |
     'for' '('ID 'in' '(' '0'','exp ')' ')' '{' stm '}'      /*check for list of exp */
     )?;

cond : exp | 'call'ID'('exp  (',' exp)* ')' ;


exp:    NUMBER                                          #valExp
        | ID                                            #derExp
        | left= exp op= ('+'|'-') right= exp            #binExp
        | left= exp op= ('>'|'==' | '>=') right= exp    #binExp
        | left= exp op= ('&&' |'*' | '/') right= exp    #binExp;


//IDs
fragment CHAR 	    : 'a'..'z' |'A'..'Z' ;
ID          : CHAR (CHAR | DIGIT)* ;

//Numbers
fragment DIGIT	    : '0'..'9';
NUMBER      : DIGIT+;

//ESCAPE SEQUENCES
WS              : (' '|'\t'|'\n'|'\r')-> skip;
LINECOMMENTS 	: '//' (~('\n'|'\r'))* -> skip;
BLOCKCOMMENTS   : '/*'( ~('/'|'*')|'/'~'*'|'*'~'/'|BLOCKCOMMENTS)* '*/' -> skip;

