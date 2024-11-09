// viewSurvey.js

const surveyTable = document.getElementById('survey-table').querySelector('tbody');
const resultsContainer = document.getElementById('results-container');
const textResponsesDiv = document.getElementById('text-responses');
const numericChartCanvas = document.getElementById('numeric-chart');
const multipleChoiceChartCanvas = document.getElementById('multiple-choice-chart');

let numericChart, multipleChoiceChart;

// Fetch the list of surveys
async function fetchSurveys() {
    try {
        const response = await fetch('/api/surveys');
        const surveys = await response.json();
        displaySurveys(surveys);
    } catch (error) {
        console.error('Error fetching surveys:', error);
    }
}

// Display surveys in the table
function displaySurveys(surveys) {
    surveyTable.innerHTML = ''; // Clear existing rows
    surveys.forEach(survey => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${survey.surveyName}</td>
            <td><button onclick="viewResults(${survey.surveyId})">View Results</button></td>
        `;
        surveyTable.appendChild(row);
    });
}

// Fetch and display survey results
async function viewResults(surveyId) {
    try {
        const response = await fetch(`/api/surveys/${surveyId}/results`);
        const results = await response.json();

        // Display text responses
        displayTextResponses(results.openEndedAnswers);

        // Display numeric range chart
        displayNumericChart(results.numericRangeAnswers);

        // Display multiple-choice chart
        displayMultipleChoiceChart(results.multipleChoiceAnswers);

        // Show the results container
        resultsContainer.style.display = 'block';
    } catch (error) {
        console.error('Error fetching survey results:', error);
    }
}

// Display text responses
function displayTextResponses(textAnswers) {
    textResponsesDiv.innerHTML = '<h3>Text Responses</h3>';
    textAnswers.forEach(answer => {
        const p = document.createElement('p');
        p.textContent = answer.text;
        textResponsesDiv.appendChild(p);
    });
}

// Display numeric range chart
function displayNumericChart(numericAnswers) {
    const data = numericAnswers.map(answer => answer.choice);
    const labels = Array.from(new Set(data)).sort((a, b) => a - b);
    const counts = labels.map(label => data.filter(value => value === label).length);

    if (numericChart) numericChart.destroy();
    numericChart = new Chart(numericChartCanvas, {
        type: 'bar',
        data: {
            labels,
            datasets: [{
                label: 'Numeric Responses',
                data: counts
            }]
        }
    });
}

// Display multiple-choice chart
function displayMultipleChoiceChart(choices) {
    const data = choices.map(answer => answer.choice);
    const labels = Array.from(new Set(data));
    const counts = labels.map(label => data.filter(value => value === label).length);

    if (multipleChoiceChart) multipleChoiceChart.destroy();
    multipleChoiceChart = new Chart(multipleChoiceChartCanvas, {
        type: 'pie',
        data: {
            labels,
            datasets: [{
                label: 'Multiple Choice Responses',
                data: counts
            }]
        }
    });
}

// Go back to survey list
function goBack() {
    resultsContainer.style.display = 'none';
}

// Initialize
fetchSurveys();
