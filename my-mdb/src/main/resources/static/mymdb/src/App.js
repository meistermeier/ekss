import React, {Component} from 'react';
import './App.css';
import CreatePerson from './CreatePerson.js';

class App extends Component {

    constructor(props) {
        super(props);

        this.state = {
            persons: [],
            isLoading: false
        };
    }

    componentDidMount() {
        this.setState({isLoading: true});

        fetch('http://localhost:8080/persons/')
            .then(response => response.json())
            .then(data => this.setState({
                persons: data,
                isLoading: false
            }));
    }


    render() {
        const {persons, isLoading} = this.state;
        if (isLoading) {
            return <p>Loading</p>
        }

        return (
            <div className="App">
                <div>
                    <h2>Persons</h2>
                    {persons.map((person) =>
                        <div key={person.firstName}>
                            {person.firstName} {person.lastName}
                        </div>
                    )}
                </div>
                <CreatePerson />
            </div>
        );
    }


}

export default App;
