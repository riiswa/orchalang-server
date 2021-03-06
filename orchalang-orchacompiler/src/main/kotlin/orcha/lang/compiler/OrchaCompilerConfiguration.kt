package orcha.lang.compiler

import orcha.lang.configuration.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OrchaCompilerConfiguration {

    @Bean
    fun orchaProgramSource(): EventHandler {
        val eventHandler = EventHandler("orchaProgramSource")
        return eventHandler;
    }

    @Bean
    fun preprocessing(): Application {
        val application = Application("preprocessing", "Kotlin")
        val javaAdapter = JavaServiceAdapter("orcha.lang.compiler.referenceimpl.PreprocessingImpl", "process")
        application.input = Input(javaAdapter, "java.lang.String")
        application.output = Output(javaAdapter, "java.util.List<java.lang.String>")
        return application
    }

    @Bean
    fun lexicalAnalysis(): Application {
        val application = Application("lexicalAnalysis", "Kotlin")
        val javaAdapter = JavaServiceAdapter("orcha.lang.compiler.referenceimpl.LexicalAnalysisImpl", "analysis")
        application.input = Input(javaAdapter, "java.util.List<java.lang.String>")
        application.output = Output(javaAdapter, "orcha.lang.compiler.OrchaProgram")
        return application
    }

    @Bean
    fun syntaxAnalysis(): Application {
        val application = Application("syntaxAnalysis", "Kotlin")
        val javaAdapter = JavaServiceAdapter("orcha.lang.compiler.referenceimpl.SyntaxAnalysisImpl", "analysis")
        application.input = Input(javaAdapter,"orcha.lang.compiler.OrchaProgram")
        application.output = Output(javaAdapter, "orcha.lang.compiler.OrchaProgram")
        return application
    }

    @Bean
    fun semanticAnalysis(): Application {
        val application = Application("semanticAnalysis", "Kotlin")
        val javaAdapter = JavaServiceAdapter("orcha.lang.compiler.referenceimpl.SemanticAnalysisImpl", "analysis")
        application.input = Input(javaAdapter,"orcha.lang.compiler.OrchaProgram")
        application.output = Output(javaAdapter,"orcha.lang.compiler.OrchaProgram")
        return application
    }

    @Bean
    fun postprocessing(): Application {
        val application = Application("postprocessing", "Kotlin")
        val javaAdapter = JavaServiceAdapter("orcha.lang.compiler.referenceimpl.PostprocessingImpl", "process")
        application.input = Input(javaAdapter,"orcha.lang.compiler.OrchaProgram")
        application.output = Output(javaAdapter, "orcha.lang.compiler.OrchaProgram")
        return application
    }

    @Bean
    fun linkEditor(): Application {
        val application = Application("linkEditor", "Kotlin")
        val javaAdapter = JavaServiceAdapter("orcha.lang.compiler.referenceimpl.springIntegration.LinkEditorImpl", "link")
        application.input = Input(javaAdapter,"orcha.lang.compiler.OrchaProgram")
        application.output = Output(javaAdapter, "orcha.lang.compiler.OrchaProgram")
        return application
    }

    @Bean
    fun outputGeneration(): Application {
        val application = Application("outputGeneration", "Kotlin")
        val javaAdapter = JavaServiceAdapter("orcha.lang.compiler.referenceimpl.springIntegration.OutputGenerationToSpringIntegration", "generation")
        application.input = Input(javaAdapter, "orcha.lang.compiler.OrchaProgram")
        return application
    }

    @Bean
    fun orchaProgramDestination(): EventHandler {
        val eventHandler = EventHandler("orchaProgramDestination")
        val fileAdapter = OutputFileAdapter("data/output", "orchaCompiler.xml")
        eventHandler.output = Output(fileAdapter, "application/xml")
        return eventHandler
    }
}