import React from "react";
import Table from "../../commons/tables/table";


const columns = [
    {
        Header: "Id",
        accessor: "id"
    },
    {
        Header: 'Name',
        accessor: 'name',
    },
    {
        Header: 'Password',
        accessor: 'pass',
    },
    {
        Header: 'Role',
        accessor: 'rol',
    }
];

const filters = [
    {
        accessor: 'name',
    }
];

class PersonTable extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            tableData: this.props.tableData
        };
    }

    render() {
        return (
            <Table
                data={this.state.tableData}
                columns={columns}
                search={filters}
                pageSize={5}
            />
        )
    }
}

export default PersonTable;