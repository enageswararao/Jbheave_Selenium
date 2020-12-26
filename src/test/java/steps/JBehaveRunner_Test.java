package steps;


import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;

import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;

import org.jbehave.core.junit.JUnitStories;

import org.jbehave.core.parsers.RegexPrefixCapturingPatternParser;

import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;

import org.jbehave.core.steps.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JBehaveRunner_Test extends JUnitStories {

    @Autowired
    private ApplicationContext applicationContext;
   @Override
   public Configuration configuration() {
        return new MostUsefulConfiguration()
               .useStoryLoader(new LoadFromClasspath(this.getClass().getClassLoader()))
               .useStoryReporterBuilder(new StoryReporterBuilder()
                        .withDefaultFormats()
                       .withFormats(Format.CONSOLE, Format.TXT, Format.HTML, Format.XML)
                       .withCrossReference(new CrossReference()))
               .useParameterConverters(new ParameterConverters()
                       .addConverters(new ParameterConverters.DateConverter(new SimpleDateFormat("yyyy-MM-dd")))) // use custom date pattern
               .useStepPatternParser(new RegexPrefixCapturingPatternParser(
                       "%")) // use '%' instead of '$' to identify parameters
               .useStepMonitor(new SilentStepMonitor());
   }

    @Override
    public InjectableStepsFactory stepsFactory() {
        ArrayList<Steps> stepFileList = new ArrayList<Steps>();
        stepFileList.add( new LoginSteps()) ;

        return new InstanceStepsFactory(configuration(), stepFileList);
    }

    @Override
    protected List<String> storyPaths() {
        return new StoryFinder().
                findPaths(CodeLocations.codeLocationFromClass(
                        this.getClass()),
                        Arrays.asList("**/*.story"),
                        Arrays.asList(""));

    }

}