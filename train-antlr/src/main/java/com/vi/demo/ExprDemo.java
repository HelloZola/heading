package com.vi.demo;

import com.vi.demo.gen.CalculatorLexer;
import com.vi.demo.gen.CalculatorParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.util.List;

public class ExprDemo {

    public static void main(String[] args) {
        // 构建字符流
        CodePointCharStream charStream = CharStreams.fromString("1+3+2+(1+4)");
        // 从字符流分析词法， 解析为token
        CalculatorLexer lexer = new CalculatorLexer(charStream);
        // 从token进行分析
        CalculatorParser parser = new CalculatorParser(new CommonTokenStream(lexer));

        CalculatorParser.ExpressionContext expressionContext = parser.expression();

        //term是g4配置里面的一个节点名称
        List<CalculatorParser.TermContext> terms = expressionContext.term();

        for (CalculatorParser.TermContext term : terms) {
            //factor是term节点里面的子节点内容
            List<CalculatorParser.FactorContext> factors = term.factor();
            System.out.println(factors.size());
        }

        System.out.println(expressionContext.term());

        // 使用监听器，遍历语法树，根据语法定义，prog为语法树的根节点
//        ProgContext prog = parser.prog();
//        ParseTreeWalker walker = new ParseTreeWalker();
//        walker.walk(new ExprBaseListener(), prog);

        // 使用visitor，生成自定义的对象
//        Object accept = prog.accept(new ExprBaseVisitor<>());

        // 打印生成的语法树
//        System.out.println(prog.toStringTree(parser));

    }

}
