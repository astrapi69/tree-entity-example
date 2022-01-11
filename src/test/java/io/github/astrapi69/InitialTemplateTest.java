/**
 * The MIT License
 *
 * Copyright (C) 2021 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.github.astrapi69;

import io.github.astrapi69.file.delete.DeleteFileExtensions;
import io.github.astrapi69.file.modify.ModifyFileExtensions;
import io.github.astrapi69.file.search.PathFinder;
import io.github.astrapi69.gradle.migration.data.CopyGradleRunConfigurations;
import io.github.astrapi69.gradle.migration.data.DependenciesInfo;
import io.github.astrapi69.gradle.migration.data.GradleRunConfigurationsCopier;
import io.github.astrapi69.io.file.filter.PrefixFileFilter;
import io.github.astrapi69.throwable.RuntimeExceptionDecorator;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

class InitialTemplateTest
{

	@Test
	@Disabled
	public void testRenameToConcreteProject() throws IOException
	{
		String projectDescription;
		// TODO change the following description with your project description
		// and then remove the annotation Disabled and run this unit test method
		projectDescription = "!!!Chage this description with your project description!!!";
		renameToConcreteProject(projectDescription);
	}

	private void renameToConcreteProject(final String projectDescription) throws IOException
	{
		String concreteProjectName;
		String templateProjectName;
		File sourceProjectDir;
		File settingsGradle;
		File dotTravisYml;
		File dotGithubDir;
		File codeOfConduct;
		File readme;
		File initialTemplateClassFile;
		//
		sourceProjectDir = PathFinder.getProjectDirectory();
		templateProjectName = "spring-boot-template";
		concreteProjectName = sourceProjectDir.getName();
		// adapt settings.gradle file
		settingsGradle = new File(sourceProjectDir, DependenciesInfo.SETTINGS_GRADLE_FILENAME);
		ModifyFileExtensions.modifyFile(settingsGradle.toPath(), (count, input) -> {
			return input.replaceAll(templateProjectName, concreteProjectName) + System
				.lineSeparator();
		});

		// change projectDescription from gradle.properties
		File gradleProperties = new File(sourceProjectDir, DependenciesInfo.GRADLE_PROPERTIES_NAME);

		ModifyFileExtensions.modifyFile(gradleProperties.toPath(), (count, input) -> {
			return input
				.replaceAll("projectDescription=Template project for create java spring boot projects",
					"projectDescription=" + projectDescription) + System.lineSeparator();
		});

		// adapt README.md file
		readme = new File(sourceProjectDir, DependenciesInfo.README_FILENAME);
		ModifyFileExtensions.modifyFile(readme.toPath(), (count, input) -> {
			return input.replaceAll(templateProjectName, concreteProjectName) + System
				.lineSeparator();
		});

		ModifyFileExtensions.modifyFile(readme.toPath(), (count, input) -> {
			return input
				.replaceAll("template project for start with spring-boot project",
					projectDescription) + System.lineSeparator();
		});

		// create run configurations for current project
		createRunConfigurations(concreteProjectName, templateProjectName, sourceProjectDir);
	}

	private void createRunConfigurations(String concreteProjectName, String templateProjectName,
		File sourceProjectDir)
	{
		String sourceProjectDirNamePrefix;
		String targetProjectDirNamePrefix;
		CopyGradleRunConfigurations copyGradleRunConfigurationsData;
		String sourceProjectName;
		String targetProjectName;
		sourceProjectName = templateProjectName;
		targetProjectName = concreteProjectName;
		sourceProjectDirNamePrefix = sourceProjectDir.getParent() + "/";
		targetProjectDirNamePrefix = sourceProjectDirNamePrefix;
		copyGradleRunConfigurationsData = GradleRunConfigurationsCopier
			.newCopyGradleRunConfigurations(sourceProjectName, targetProjectName,
				sourceProjectDirNamePrefix, targetProjectDirNamePrefix, true, true);
		GradleRunConfigurationsCopier.of(copyGradleRunConfigurationsData).copy();

		// delete template run configurations
		RuntimeExceptionDecorator.decorate(() -> DeleteFileExtensions
			.deleteFilesWithFileFilter(copyGradleRunConfigurationsData.getIdeaTargetDir(),
				new PrefixFileFilter("spring_boot_template", false)));
	}


}
