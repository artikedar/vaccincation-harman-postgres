import React, { Component } from "react";
import { connect } from "react-redux";
import {Table, Thead, Tbody, Tr, Th, Td } from 'react-super-responsive-table';
import 'react-super-responsive-table/dist/SuperResponsiveTableStyle.css';
import getReportsAction from "./bReportAction";
import getCitiesAction from "../Employee/getCitiesAction";
import sf from "../../safeTraverse";
import './Report.css';
import getCSVAction from './csvAction';

 
export class BookingReport extends Component {

constructor(props) {
    super(props)

    const { empid } = this.props;

    this.state = {  
       allRep: [],
        dep : [] ,
        selectedCity: ""  
    }
}


componentDidMount() {
    this.getCities();
}


getCities() {
    this.props.getCities();
  }

getReports(city){
    const loc = city;
    console.log(city);
    this.props.getReports(loc, (data) => {
        this.setState({allRep: data});
       // console.log(data);
    });
}

handleCityChange = (e) => {
    e.preventDefault();
    const { value } = e.target;
    this.setState({ selectedCity: Number(value)}, ()=> {
        console.log(this.state.selectedCity);
    });
    if (e.target.value > 0) {
        this.getReports(value);
      }
  };

  render() {

    const allCities = sf(this, [
        "props",
        "cities",
        "location",
        "resp",
        "_embedded",
        "lov",
      ]);
     // console.log(allCities);

    const wantedData = sf(this, [ "state", "allRep", "data"]);

    return (
        <div classame="hr-main">
            <div className="emp-main">
               <div className="emp-info">
                <div className="dependentList">
                  <div className="heading">
                  <h2>Vaccine Booking Report</h2>
                  </div>
                  <div>
                    <div className="city">
                    <label htmlFor="floatingInput">City</label>
                    <select
                        className="form-select form-select-sm"
                        aria-label=".form-select-sm example"
                        onChange={this.handleCityChange}
                        id="selectcity"
                    >
                        <option default value={0}>
                            Select City
                        </option>
                        {(allCities || []).map((item) => (
                            <option key={item.valueid} value={item.valueid}>
                            {sf(item, ["value"]) || ""}
                            </option>
                        ))}
                    </select>
                    </div>
                  </div>
                  { this.state.selectedCity>0 ? (
                    <Table striped bordered hover size="sm" variant = "dark" id="repTable">
                      <Thead>
                      <Tr>
                      <Th>Date</Th>
                      <Th>Total Doses</Th>
                      <Th>AvailableDoses</Th>
                      <Th>Booked Doses</Th>
                      </Tr>
                      </Thead>    
                      {(wantedData || []).map((item) => (
                      <Tbody>
                          <Td key={item.name}>{sf(item, ["dateOfAvailability"]) || ""}</Td>  
                          <Td key={item.name}>{sf(item, ["totalNoOfDoses"]) || ""}</Td>
                          <Td key={item.name}>{sf(item, ["noOfAvailableDoses"]) || ""}</Td>
                          <Td key={item.name}>{sf(item, ["noOfBookedDoses"]) || ""}</Td>
                      </Tbody>))}
                    </Table>)
                    : null}             
                </div>
            </div>
            </div>
        </div>
    )
  }
}


export const mapStateToProps = (state) => ({
    breport: state.breport,
    areport: state.areport,
    cities: state.cities,
    csv: state.csv
  });

  export const mapDispatchToProps = (dispatch) => ({
    getReports: (loc, cb) => {
        dispatch(getReportsAction(loc, cb));
    },
    getCities: () => {
        dispatch(getCitiesAction());
      },
      getCSV: () => {
        dispatch(getCSVAction());
    }
  });

  export default connect(mapStateToProps, mapDispatchToProps)(BookingReport);
  