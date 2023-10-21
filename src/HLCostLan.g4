grammar HLCostLan;

prg : complexType* serviceDecl* functionDecl* init;

init: '('formalParams? ')''=''>' '{' stm '}';
/* p contains the list of paramters of the function */

//Declaration Services
//service PremiumService: (Params) -> string;
serviceDecl: 'service'ID':''('(type(','type)*)?')''->'type';';

functionDecl : 'fn'ID'(' formalParams?  ')' '->' (type)'{'stm'}' ;

stm :(
     | callService
     |'if' '(' cond ')' '{'stm '}' 'else' '{' stm '}'
     |'for' '('ID 'in' '(' NUMBER','exp ')' ')' '{' stm '}'   /*check for list of ast.exp */
     | letIn
     | ID'('listExp')'
     |structAssignment
     |exp
     );

callService : 'call'ID'('(exp(','exp)*)?')' (';' stm)?;

letIn: 'let' (assignment)+ 'in' structAssignment* stm;

cond : exp | 'call'ID'('(exp  (',' exp)*)? ')' ;

listExp : exp(',' exp)* ;

exp:     left= exp op= ('+'|'-') right= exp                    #binExp
        | left= exp op= ('>'|'==' | '>=' |'!=') right= exp      #binExp
        | left= exp op= ('&&' |'*' | '/') right= exp            #binExp
        | ID'.'ID                                       #callFun
        | '"' STRING                                #stringExp
        | NUMBER                                                #valExp
        | ID                                                    #derExp;

assignment: (structType)? (ID '=' assign) ( ID '=' assign)*;
structType: ID;

type : basictype | ID;

basictype: 'int'
    | 'char'
    | 'string'
    | 'bool'
    | 'float'
    | 'any'
    | 'void';

// `<type> <= any` for all types
complexType : 'struct'ID'{' ID ':' (arrayType) (',' ID ':' (arrayType))* '}';

arrayType : 'array''['typeArr']' | type;
typeArr : type;

structAssignment : ID'{'ID ':' assign (','ID ':' assign)*'}';

assign : (exp';'|stm);
params :  ID','  params | ID ;
formalParams:  ID ':' type(','ID ':' type)*;

//IDs
fragment CHAR 	    : 'a'..'z' |'A'..'Z' ;
ID          : CHAR (CHAR | DIGIT)* ;
//Numbers
fragment DIGIT	    : '0'..'9';
NUMBER      : DIGIT+;

//String
STRING      :  ID(ID | ' ')* CLS_STR ;

CLS_STR : '"' ;
//ESCAPE SEQUENCES
NEWLINE   :  ( '\r\n'   // DOS
               | '\r'   // MAC
               | '\n'   // Unix
             )
             ->skip;
LINECOMMENTS 	: '//' (~('\n'|'\r'))* -> skip;
WS         :    (' '|'\t')+ -> skip;
BLOCKCOMMENTS   : '/*'( ~('/'|'*')|'/'~'*'|'*'~'/'|BLOCKCOMMENTS)* '*/' -> skip;
LINE_NUMBER : [0-9]+;

