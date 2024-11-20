import React from 'react';

class NotificationPopup extends React.Component {
    render() {
        const { message, onClose } = this.props;

        return (
            <div className="popup">
                <div className="popup-content">
                    <p>{message}</p>
                    <button onClick={onClose}>Close</button>
                </div>
            </div>
        );
    }
}
