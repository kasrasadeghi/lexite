program -> 
    definition*

definition -> 
    type name ( ) block

block -> 
    { statement* }
    
type -> 
    Word
    
name -> 
    Word
    
statement ->
    print ( StringLiteral )