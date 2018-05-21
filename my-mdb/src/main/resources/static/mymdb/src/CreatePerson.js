import React, {Component} from 'react';

class CreatePerson extends Component {
    constructor(props) {
        super(props);
        this.state={
            firstName:'',
            lastName:''
        }
    }

    updateFirstName(value) {
        this.setState({
            firstName:value
        });
    }


    updateLastName(value) {
        this.setState({
            lastName:value
        });
    }

    save() {
        fetch('http://localhost:8080/persons/',
            {
                method: 'POST',
                body: JSON.stringify(this.state),
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(response => window.location.reload())
            .catch(err => err);
    }

    render() {
        return (
            <div>
                <input value={this.state.firstName}
                    onChange={event => this.updateFirstName(event.target.value)} />
                <input value={this.state.lastName}
                    onChange={event => this.updateLastName(event.target.value)} />

                <button type="submit" onClick={() => this.save()}>Speichern</button>
            </div>
        )
    }
}

export default CreatePerson;
