import React from "react";
import Table from "../../commons/tables/table";



const columns = [
    {
        Header: "Id",
        accessor: "id"
    },
    {
        Header: 'Description',
        accessor: 'description',
    },
    {
        Header: 'Address',
        accessor: 'address',
    },
    {
        Header: 'MaxHours',
        accessor: 'maxHours',
    },
    {
        Header: 'Username',
        accessor: 'user',
    }
];

const filters = [
    {
        accessor: 'user',
    }
];

class DeviceTable extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            tableData2: this.props.tableData2
        };
    }

    render() {
        return (
            <Table
                data={this.state.tableData2}
                columns={columns}
                search={filters}
                pageSize={5}
            />
        )
    }
}

export default DeviceTable;