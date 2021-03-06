package orcha.lang.compiler

import orcha.lang.compiler.syntax.Instruction

import java.util.ArrayList

class IntegrationNode(val instruction: Instruction? = null) {

    var integrationPattern: IntegrationPattern? = null

    //val instruction: Instruction? = instruction

    var nextIntegrationNodes: List<IntegrationNode> = ArrayList()

    enum class IntegrationPattern {
        CHANNEL_ADAPTER,
        MESSAGE_FILTER,
        MESSAGE_TRANSLATOR,
        MESSAGE_ROUTER,
        SERVICE_ACTIVATOR,
        AGGREGATOR,
        RESEQUENCER
    }

    override fun toString(): String {
        return "IntegrationNode(integrationPattern=$integrationPattern, instruction=$instruction, nextIntegrationNodes=$nextIntegrationNodes)"
    }

}
