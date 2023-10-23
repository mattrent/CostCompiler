grammar HLCostLan;

prg : complexType* serviceDecl* functionDecl* init;

init: '('formalParams? ')''=''>' '{' stm '}';


serviceDecl: 'service'ID':''('(type(','type)*)?')''->'type';';

functionDecl : 'fn'ID'(' formalParams?  ')' '->' (type)'{'stm'}' ;

stm :
     | serviceCall
     |'if' '(' expOrCall ')' '{'stm '}' 'else' '{' stm '}'
     |'for' '('ID 'in' '(' NUMBER','exp ')' ')' '{' stm '}'   /*check for list of ast.exp */
     | letIn
     | functionCall
     | 'return' expPlus ;

serviceCall : 'call'ID'('(exp(','exp)*)?')' (';' stm)?;

functionCall : ID'('( exp (','exp)* )? ')';

letIn: 'let' (ID '=' expPlus)+ 'in' stm;

expOrCall : exp | serviceCall ;


expPlus	:	exp
			| serviceCall
			| functionCall
			| ID '{' ID ':' expPlus (',' ID ':' expPlus)* '}'
			;

exp:     left= exp op= ('+'|'-') right= exp                    #binExp
        | left= exp op= ('>'|'==' | '>=' |'!=') right= exp      #binExp
        | left= exp op= ('&&' |'*' | '/') right= exp            #binExp
        | ID'.'ID                                       #callFun
        | '"' STRING                                #stringExp
        | NUMBER                                                #valExp
        | ID                                                    #derExp;

type : basictype | ID;

basictype: 'int'
    | 'char'
    | 'string'
    | 'bool'
    | 'float'
    | 'any'
    | 'void';

// `<type> <= any` for all types
complexType : 'struct'ID'{' ID ':' (structType) (',' ID ':' (structType))* '}'
            |  ID ':' type'['NUMBER']';

structType : 'array''['typeArr']' | type;
typeArr : type;

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

