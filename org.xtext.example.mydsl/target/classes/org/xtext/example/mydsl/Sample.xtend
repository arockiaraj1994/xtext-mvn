package org.xtext.example.mydsl

import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.xtext.util.CancelIndicator
import org.eclipse.xtext.util.StringInputStream
import org.eclipse.xtext.xbase.interpreter.impl.DefaultEvaluationContext
import org.eclipse.xtext.xbase.interpreter.impl.XbaseInterpreter
import org.xtext.example.mydsl.myDsl.Rule
import org.xtext.example.mydsl.myDsl.Model

class Sample {
	def static void main(String[] args) {
        val userInput = '''
        Rule rule1 From sftp To sftp
        Rule rule2 From activeMQ To sftp
        '''
        val injector = new MyDslStandaloneSetup().createInjectorAndDoEMFRegistration
        val resourceSet = injector.getInstance(ResourceSet)
        val resource = resourceSet.createResource(URI.createURI("dummy.mydsl"))
        resource.load(new StringInputStream(userInput), null)
        val model = resource.contents.head as Model
        val interpreter = injector.getInstance(XbaseInterpreter)
        println(model.rules);
        for (g : model.rules) {
            val ctx = new DefaultEvaluationContext
            //val result = interpreter.evaluate(g., ctx, CancelIndicator.NullImpl)
            println(g)
        }
    }
}