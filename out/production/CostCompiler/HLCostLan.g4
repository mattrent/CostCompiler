grammar HLCostLan;

main : complexType* declarationService* fund*;
/* p contains the list of paramters of the function */

//Declaration Services
//service PremiumService: (Params) -> string;
declarationService: 'service'ID':''('typeDecl?(','typeDecl)*')''->'typeDecl';';
typeDecl : (type | ID);

fund : 'fn'ID'(' formalParams?  ')' '->' (ID|type)'{'stm'}' ;

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
        | '"'ID'"'                                              #stringExp
        | NUMBER                                                  #valExp
        | ID                                                    #derExp;

assignment: (structType)? (ID '=' assign) ( ID '=' assign)*;
structType: ID;

type: 'int'
    | 'char'
    | 'string'
    | 'bool'
    | 'float'
    | 'any'
    | 'void'
    | 'null';

// `<type> <= any` for all types
complexType : 'struct'ID'{' ID ':' (arrayType) (',' ID ':' (arrayType))* '}';

arrayType : 'array''['typeArr']' | type;
typeArr : type;

structAssignment : ID'{'ID ':' assign (','ID ':' assign)*'}';

assign : (exp';'|stm);
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

