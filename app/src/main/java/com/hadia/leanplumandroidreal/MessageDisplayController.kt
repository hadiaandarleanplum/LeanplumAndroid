package com.hadia.leanplumandroidreal

import com.leanplum.ActionContext

com.leanplum.Leanplum;

interface MessageDisplayController {
    /**
     * Called per message to decide whether to show, discard or delay it.
     */
    fun shouldDisplayMessage(action: ActionContext): MessageDisplayChoice?

    /**
     * Called when there are multiple messages to be displayed.
     * We can order or remove any of them that we don't want to present.
     */
    fun prioritizeMessages(actions: List<ActionContext>, trigger: ActionsTrigger?): List<ActionContext>
}

