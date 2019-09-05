package org.xtext.example.mydsl;

import com.google.inject.Injector;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.util.StringInputStream;
import org.eclipse.xtext.xbase.interpreter.impl.DefaultEvaluationContext;
import org.eclipse.xtext.xbase.interpreter.impl.XbaseInterpreter;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.xtext.example.mydsl.MyDslStandaloneSetup;
import org.xtext.example.mydsl.myDsl.Model;
import org.xtext.example.mydsl.myDsl.Rule;

@SuppressWarnings("all")
public class Sample {
  public static void main(final String[] args) {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Rule rule1 From sftp To sftp");
      _builder.newLine();
      _builder.append("Rule rule2 From activeMQ To sftp");
      _builder.newLine();
      final String userInput = _builder.toString();
      final Injector injector = new MyDslStandaloneSetup().createInjectorAndDoEMFRegistration();
      final ResourceSet resourceSet = injector.<ResourceSet>getInstance(ResourceSet.class);
      final Resource resource = resourceSet.createResource(URI.createURI("dummy.mydsl"));
      StringInputStream _stringInputStream = new StringInputStream(userInput);
      resource.load(_stringInputStream, null);
      EObject _head = IterableExtensions.<EObject>head(resource.getContents());
      final Model model = ((Model) _head);
      final XbaseInterpreter interpreter = injector.<XbaseInterpreter>getInstance(XbaseInterpreter.class);
      InputOutput.<EList<Rule>>println(model.getRules());
      EList<Rule> _rules = model.getRules();
      for (final Rule g : _rules) {
        {
          final DefaultEvaluationContext ctx = new DefaultEvaluationContext();
          InputOutput.<Rule>println(g);
        }
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
