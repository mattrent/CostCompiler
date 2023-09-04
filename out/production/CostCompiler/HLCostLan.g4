grammar HLCostLan;

main : complexType* declarationService* fund*;

fund : 'fn'ID'(' formalParams  ')' '->' (ID|type)'{'stm'}' ;

/* p contains the list of paramters of the function */

//Declaration Services
//service PremiumService: (Params) -> string;
declarationService: 'service'ID':''('type* | ID(','ID)*')''->'(type | ID)';';

stm :(
     |'call'ID'('exp(','exp)*')' stm?';'
     |'if' '(' cond ')' '{'stm '}' 'else' '{' stm '}'
     |'for' '('ID 'in' '(' '0'','exp ')' ')' '{' stm '}'   /*check for list of exp */
     |'for''('listCount';'exp';'listExp')''{' stm '}'    //for iterator
     | letIn
     | ID'('listCount')'';' //perche non puo essre listExp?
     );

letIn: 'let' ((ID':')? assignment)+ 'in' structAssignment* stm;

cond : exp | 'call'ID'('exp  (',' exp)* ')' ;

listCount : ID','  (params | ID)* ;
listExp : exp','  (params | exp)* ;

exp:    NUMBER                                          #valExp
        | ID                                            #derExp
        | left= exp op= ('+'|'-') right= exp            #binExp
        | left= exp op= ('>'|'==' | '>=' |'!=') right= exp    #binExp
        | left= exp op= ('&&' |'*' | '/') right= exp    #binExp
        | ID'.'ID('.'ID)*                               #callFun
        | '"'ID'"'                                 #stringExp;

assignment: ID '=' (exp';'|stm) (ID '=' (exp';'|stm))*;

type: 'int'
    | 'char'
    | 'string'
    | 'bool'
    | 'float'
    | 'any'
    | 'void';

// `<type> <= any` for all types
complexType : 'array''['type']'
            | 'struct'ID'{' ID ':' (type|'array''['type']') (',' ID ':' (type|'array''['type']'))* '}';

structAssignment : ID'{'ID ':' (exp|stm)(','ID ':' (exp|stm))*'}';

params :  ID','  params | ID ;
formalParams:  ID ':' (type|ID)(','ID ':' (type|ID))*;

//IDs
fragment CHAR 	    : 'a'..'z' |'A'..'Z' ;
ID          : CHAR (CHAR | DIGIT)* ;
STRING      : CHAR (CHAR)* ;

//Numbers
fragment DIGIT	    : '0'..'9';
NUMBER      : DIGIT+;

//ESCAPE SEQUENCES
NEWLINE   :  ( '\r\n'   // DOS
               | '\r'   // MAC
               | '\n'   // Unix
             )
             ->skip;
LINECOMMENTS 	: '//' (~('\n'|'\r'))* -> skip;
WS         : (' '|'\t')+ -> skip;
BLOCKCOMMENTS   : '/*'( ~('/'|'*')|'/'~'*'|'*'~'/'|BLOCKCOMMENTS)* '*/' -> skip;
LINE_NUMBER : [0-9]+;
