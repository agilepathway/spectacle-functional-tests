package com.thoughtworks.gauge.test.implementation;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;
import com.thoughtworks.gauge.test.common.Scenario;
import com.thoughtworks.gauge.test.common.Specification;

import java.util.List;

import static com.thoughtworks.gauge.test.common.GaugeProject.currentProject;

public class SpecAndScenarioCreation {

    @Step("Create <scenario name> in <spec name> with the following steps <steps table>")
    public void addContextToSpecification(String scenarioName, String specName, Table steps) throws Exception {
        createScenario(scenarioName, specName, "", false, steps);
    }

    @Step("Create <scenario name> in <spec name> within sub folder <subFolder> with the following steps <steps table>")
    public void addContextToSpecification(String scenarioName, String specName, String subFolder, Table steps) throws Exception {
        createScenario(scenarioName, specName, subFolder, false, steps);
    }

    @Step("Create a scenario <scenario name> in specification <spec name> with the following continue on failure steps <table>")
    public void createScenarioWithContinueSteps(String scenarioName, String specName, Table steps) throws Exception {
        createScenario(scenarioName, specName, "", true, steps);
    }

    @Step("Create a specification <spec name> with the following datatable <table>")
    public void createSpecWithDataTable(String specName, Table datatable) throws Exception {
        Specification specification = currentProject.createSpecification(specName);
        specification.addContextSteps();
        specification.addDataTable(datatable);
        specification.save();
    }

    @Step("Create step implementations <table>")
    public void createStepImplementations(Table steps) throws Exception {
        List<String> columnNames = steps.getColumnNames();
        if (columnNames.size() < 2) {
            throw new Exception("Expecting table with at least 2 columns: steps and implementations");
        }
        for (TableRow row : steps.getTableRows()) {
            boolean continueOnFailure = false;
            if (!row.getCell("continue").isEmpty()){
                continueOnFailure = Boolean.parseBoolean(row.getCell("continue"));
            }
            currentProject.implementStep(row.getCell(columnNames.get(0)), row.getCell(columnNames.get(1)), continueOnFailure, false);
        }
    }

    private boolean shouldCreateImplementation(TableRow row, List<String> columnNames) {
        if (columnNames.size() < 2) {
            return false;
        }
        String implementation = row.getCell(columnNames.get(1));
        if (implementation == null) {
            return false;
        } else if (!implementation.isEmpty()) {
            return true;
        }
        return false;
    }

    private void createScenario(String scenarioName, String specName, String subFolder, boolean isContinueOnFailure, Table steps) throws Exception {
        Specification spec = currentProject.findSpecification(specName);
        if (spec == null) {
            spec = currentProject.createSpecification(subFolder, specName);
        }
        List<String> columnNames = steps.getColumnNames();
        Scenario scenario = new Scenario(scenarioName);
        for (TableRow row : steps.getTableRows()) {
            scenario.addItem(row.getCell(columnNames.get(0)), row.getCell("Type"));
            boolean b = shouldCreateImplementation(row, columnNames);
            if (b) {
                if (isContinueOnFailure) isContinueOnFailure = Boolean.parseBoolean(row.getCell(columnNames.get(2)));
                currentProject.implementStep(row.getCell(columnNames.get(0)), row.getCell(columnNames.get(1)), isContinueOnFailure, false);
            }
        }
        spec.addScenarios(scenario);
        spec.save();
    }
}