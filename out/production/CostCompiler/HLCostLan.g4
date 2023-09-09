grammar HLCostLan;

main : complexType* declarationService* fund*;
/* p contains the list of paramters of the function */

//Declaration Services
//service PremiumService: (Params) -> string;
declarationService: 'service'ID':''('typeDecl?(','typeDecl)*')''->'typeDecl';';
typeDecl : (type | ID);

fund : 'fn'ID'(' formalParams  ')' '->' (ID|type)'{'stm'}' ;

stm :(
     | callService
     |'if' '(' cond ')' '{'stm '}' 'else' '{' stm '}'
     |'for' '('ID 'in' '(' '0'','exp ')' ')' '{' stm '}'   /*check for list of exp */
     | letIn
     | ID'('listExp')'';'
     );

callService : 'call'ID'('(exp(','exp)*)?')' ';' stm?;

letIn: 'let' ((ID':')? assignment)+ 'in' structAssignment* stm;

cond : exp | 'call'ID'('exp  (',' exp)* ')' ;

listExp : exp','  (params | exp)* ;

exp:    NUMBER                                                  #valExp
        | ID                                                    #derExp
        | left= exp op= ('+'|'-') right= exp                    #binExp
        | left= exp op= ('>'|'==' | '>=' |'!=') right= exp      #binExp
        | left= exp op= ('&&' |'*' | '/') right= exp            #binExp
        | ID'.'ID                                       #callFun
        | '"'ID'"'                                              #stringExp;

assignment: ID '=' (exp';'|stm) (ID '=' (exp';'|stm))*;

type: 'int'
    | 'char'
    | 'string'
    | 'bool'
    | 'float'
    | 'any'
    | 'void';

// `<type> <= any` for all types
complexType : 'array''['typeArr']'
            | 'struct'ID'{' ID ':' (arrayType) (',' ID ':' (arrayType))* '}';

arrayType : 'array''['typeArr']' | type;
typeArr : type;

structAssignment : ID'{'ID ':' (exp|stm)(','ID ':' (exp|stm))*'}';

params :  ID','  params | ID ;
formalParams:  ID ':' typeDecl(','ID ':' typeDecl)*;

//IDs
fragment CHAR 	    : 'a'..'z' |'A'..'Z' ;
ID          : CHAR (CHAR | DIGIT)* ;

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

